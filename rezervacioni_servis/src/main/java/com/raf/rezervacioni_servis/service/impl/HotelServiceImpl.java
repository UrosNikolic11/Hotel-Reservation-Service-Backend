package com.raf.rezervacioni_servis.service.impl;

import com.raf.rezervacioni_servis.domain.Hotel;
import com.raf.rezervacioni_servis.domain.Ocena;
import com.raf.rezervacioni_servis.domain.Soba;
import com.raf.rezervacioni_servis.domain.Tip;
import com.raf.rezervacioni_servis.dto.*;
import com.raf.rezervacioni_servis.mapper.HotelMapper;
import com.raf.rezervacioni_servis.mapper.OcenaMapper;
import com.raf.rezervacioni_servis.mapper.SobaMapper;
import com.raf.rezervacioni_servis.repository.HotelRepository;
import com.raf.rezervacioni_servis.repository.OcenaRepository;
import com.raf.rezervacioni_servis.repository.SobaRepository;
import com.raf.rezervacioni_servis.repository.TipRepository;
import com.raf.rezervacioni_servis.service.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private OcenaRepository ocenaRepository;
    private SobaRepository sobaRepository;
    private TipRepository tipRepository;
    private HotelMapper hotelMapper;
    private OcenaMapper ocenaMapper;
    private SobaMapper sobaMapper;

    public HotelServiceImpl(TipRepository tipRepository, HotelRepository hotelRepository, OcenaRepository ocenaRepository, SobaRepository sobaRepository, HotelMapper hotelMapper, OcenaMapper ocenaMapper, SobaMapper sobaMapper) {
        this.hotelRepository = hotelRepository;
        this.ocenaRepository = ocenaRepository;
        this.sobaRepository = sobaRepository;
        this.hotelMapper = hotelMapper;
        this.ocenaMapper = ocenaMapper;
        this.sobaMapper = sobaMapper;
        this.tipRepository = tipRepository;
    }

    @Override
    public HotelDto addHotel(HotelCreateDto hotelCreateDto) {
        Hotel hotel = hotelMapper.hotelCreateDtoToHotel(hotelCreateDto);
        hotel.setManager_id(hotelCreateDto.getManager_id());
        hotelRepository.save(hotel);
        return hotelMapper.hotelToHotelDto(hotel);
    }

    @Override
    public HotelDto updateHotel(Long id, HotelUpdateDto hotelUpdateDto) {
        Hotel hotel = hotelRepository.findHotelById(id);
        if(!(hotelUpdateDto.getOpis() == null)){
            hotel.setOpis(hotelUpdateDto.getOpis());
        }
        if(!(hotelUpdateDto.getGrad() == null)){
            hotel.setGrad(hotelUpdateDto.getGrad());
        }
        if(!(hotelUpdateDto.getManager_id() == null)){
            hotel.setManager_id(hotelUpdateDto.getManager_id());
        }
        if(!(hotelUpdateDto.getIme() == null)){
            hotel.setIme(hotelUpdateDto.getIme());
        }
        hotelRepository.save(hotel);
        return hotelMapper.hotelToHotelDto(hotel);
    }

    @Override
    public OcenaDto addOcena(OcenaCreateDto ocenaCreateDto) {
        Hotel hotel = hotelRepository.findHotelByIme(ocenaCreateDto.getImeHotela());
        Ocena ocena = new Ocena();
        ocena.setHotel_id(hotel.getId());
        ocena.setOcena(ocenaCreateDto.getOcena());
        ocena.setKomentar(ocenaCreateDto.getOpis());
        ocena.setKlijent_id(ocenaCreateDto.getKlijentId());
        ocenaRepository.save(ocena);
        return ocenaMapper.ocenaToOcenaDto(ocena);
    }

    @Override
    public void deleteOcena(Long id) {
        ocenaRepository.deleteById(id);
    }

    @Override
    public SobaDto addSoba(SobaCreateDto sobaCreateDto) {
        Soba sobica = null;
        Hotel hotel = hotelRepository.findHotelByIme(sobaCreateDto.getHotel());
        Tip tip = tipRepository.findTipByNaziv(sobaCreateDto.getTip());
        List<Soba> s = sobaRepository.findSobasByHotelIdAndTipIdOrderByBrSobeDesc(hotel.getId(), tip.getId());
        if(s.size() == 0){
          Integer odakle = tip.getOdakle();
          Integer dokle = sobaCreateDto.getKolikoSoba() + tip.getOdakle();
          if(dokle < tip.getDokle()){
              for(int i = odakle; i< dokle; i++){
                  Soba soba = new Soba();
                  soba.setHotelId(hotel.getId());
                  soba.setTipId(tip.getId());
                  soba.setBrSobe(i);
                  sobaRepository.save(soba);
                  sobica = soba;
              }
          }
        }
        else {
            Soba ss = s.get(0);
            Integer odakle = ss.getBrSobe() +1;
            Integer dokle = sobaCreateDto.getKolikoSoba() + odakle;
            if(dokle < tip.getDokle()){
                for(int i = odakle; i< dokle; i++){
                    Soba soba = new Soba();
                    soba.setHotelId(hotel.getId());
                    soba.setTipId(tip.getId());
                    soba.setBrSobe(i);
                    sobaRepository.save(soba);
                    sobica = soba;
                }
            }
        }
        return sobaMapper.sobaToSobaDto(sobica);
    }


}
