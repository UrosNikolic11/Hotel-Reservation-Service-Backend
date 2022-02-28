package com.raf.rezervacioni_servis.service.impl;

import com.raf.rezervacioni_servis.domain.Rezervacija;
import com.raf.rezervacioni_servis.domain.Soba;
import com.raf.rezervacioni_servis.domain.Termin;
import com.raf.rezervacioni_servis.domain.Tip;
import com.raf.rezervacioni_servis.dto.*;
import com.raf.rezervacioni_servis.listener.helper.MessageHelper;
import com.raf.rezervacioni_servis.mapper.RezervacijaMapper;
import com.raf.rezervacioni_servis.repository.RezervacijaRepository;
import com.raf.rezervacioni_servis.repository.SobaRepository;
import com.raf.rezervacioni_servis.repository.TerminRepository;
import com.raf.rezervacioni_servis.repository.TipRepository;
import com.raf.rezervacioni_servis.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;
    private RezervacijaMapper rezervacijaMapper;
    private SobaRepository sobaRepository;
    private TipRepository tipRepository;
    private TerminRepository terminRepository;
    private RestTemplate userServiceApiClient;
    private JmsTemplate jmsTemplate;
    private String plusRezervationDestination;
    private String minusRezervationDestination;
    private MessageHelper messageHelper;
    private String rezervisano;
    private String otkazano;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper, SobaRepository sobaRepository, TipRepository tipRepository, TerminRepository terminRepository
            ,@Qualifier("userServiceApiClient") RestTemplate userServiceApiClient, JmsTemplate jmsTemplate
    ,@Value("${destination.plusRezervation}") String plusRezervationDestination
            ,@Value("${destination.minusRezervation}") String minusRezervationDestination
    ,MessageHelper messageHelper, @Value("${destination.rezervisano}") String rezervisano, @Value("${destination.otkazano}") String otkazano) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
        this.sobaRepository = sobaRepository;
        this.tipRepository = tipRepository;
        this.terminRepository = terminRepository;
        this.userServiceApiClient = userServiceApiClient;
        this.jmsTemplate = jmsTemplate;
        this.plusRezervationDestination = plusRezervationDestination;
        this.minusRezervationDestination = minusRezervationDestination;
        this.messageHelper = messageHelper;
        this.rezervisano = rezervisano;
        this.otkazano = otkazano;
    }

    @Override
    public RezervacijaDto addRezervacija(RezervacijaCreateDto rezervacijaCreateDto) {
        ResponseEntity<DiscountDto> discontDto = userServiceApiClient.exchange("/user/discount/" + rezervacijaCreateDto.getKlijentId(), HttpMethod.GET,
                null, DiscountDto.class);
        ResponseEntity<UserDto> userDto = userServiceApiClient.exchange("/user/getUser/" + rezervacijaCreateDto.getKlijentId(), HttpMethod.GET,
                null, UserDto.class);
        System.out.println("random " + userDto.getBody().getEmail());
        if(userDto == null){
            return null;
        }

        Float popust = discontDto.getBody().getDiscount();
        List<Rezervacija> rezervacije = rezervacijaRepository.findAll();
        for(Rezervacija r : rezervacije){
            if(r.getSoba_id() == rezervacijaCreateDto.getSobaId() && r.getTermin_id() == rezervacijaCreateDto.getTerminId()){
                return null;
            }
        }
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setTermin_id(rezervacijaCreateDto.getTerminId());
        rezervacija.setSoba_id(rezervacijaCreateDto.getSobaId());
        rezervacija.setKlijent_id((rezervacijaCreateDto.getKlijentId()));//ide iz tokena
        Soba soba = sobaRepository.findSobaById(rezervacijaCreateDto.getSobaId());
        Tip tip = tipRepository.findTipById(soba.getTipId());
        Termin termin = terminRepository.findTerminById(rezervacijaCreateDto.getTerminId());
        Float cenaPoDanu = tip.getCena();
        Date datum1 = termin.getStartDate();
        Date datum2 = termin.getEndDate();
        long diff = datum2.getTime() - datum1.getTime();
        long brDana = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        rezervacija.setCena(brDana*cenaPoDanu*(1-popust));
        rezervacijaRepository.save(rezervacija);
        jmsTemplate.convertAndSend(plusRezervationDestination,messageHelper.createTextMessage(new PlusReservationDto(rezervacija.getKlijent_id())));
        jmsTemplate.convertAndSend(rezervisano, messageHelper.createTextMessage(new UserDto(userDto.getBody().getId(), userDto.getBody().getEmail(), userDto.getBody().getFirstName(), userDto.getBody().getLastName(), userDto.getBody().getUsername(), userDto.getBody().getTelefon(), userDto.getBody().getDatumRodjenja())));
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    @Override
    public void deleteRezervacija(Long id) {
        Rezervacija rezervacija = rezervacijaRepository.findRezervacijaById(id);
        rezervacijaRepository.deleteById(id);
        ResponseEntity<UserDto> userDto = userServiceApiClient.exchange("/user/getUser/" + rezervacija.getKlijent_id(), HttpMethod.GET,
                null, UserDto.class);
        jmsTemplate.convertAndSend(minusRezervationDestination,messageHelper.createTextMessage(new PlusReservationDto(rezervacija.getKlijent_id())));
        jmsTemplate.convertAndSend(otkazano, messageHelper.createTextMessage(new UserDto(userDto.getBody().getId(), userDto.getBody().getEmail(), userDto.getBody().getFirstName(), userDto.getBody().getLastName(), userDto.getBody().getUsername(), userDto.getBody().getTelefon(), userDto.getBody().getDatumRodjenja())));
    }

    @Override
    public Page<RezervacijaDto> findRezervacija(Pageable pageable) {
        return rezervacijaRepository.findAll(pageable).map(rezervacijaMapper::rezervacijaToRezervacijaDto);
    }
}
