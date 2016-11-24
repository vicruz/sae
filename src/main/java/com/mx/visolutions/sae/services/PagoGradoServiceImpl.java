package com.mx.visolutions.sae.services;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.dto.PagoGradoRelForm;
import com.mx.visolutions.sae.entities.CatPagos;
import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.repositories.PagoGradoRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PagoGradoServiceImpl implements PagoGradoService {
	
	private PagoGradoRepository pagoGradoRepository;
	
	@Autowired
	public PagoGradoServiceImpl(PagoGradoRepository pagoGradoRepository){
		this.pagoGradoRepository = pagoGradoRepository;
	}

	@Override
	public List<PagoGrado> getByIdGrado(Integer idGrado) {
		return  pagoGradoRepository.findByIdGrado(idGrado);
	}

	@Override
	public List<PagoGrado> findAll() {
		return pagoGradoRepository.findAll();
	}

	@Override
	public void addNew(PagoGradoRelForm pagoGrado) throws Throwable {
		// TODO Auto-generated method stub
		PagoGrado pago= new PagoGrado();
		CatPagos catpago = new CatPagos();
		catpago.setId(pagoGrado.getIdPago());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fechaLimite =formatter.parse(pagoGrado.getFechaLimite());
		int anio = Integer.valueOf(pagoGrado.getAnio());
		
		pago.setIdGrado(pagoGrado.getIdGrado());
		pago.setFechaLimite(fechaLimite);
		pago.setAnio_corresponde(anio);
		pago.setMes_corresponde(pagoGrado.getMes());
		pago.setCatPago(catpago);

		pagoGradoRepository.save(pago);
	}


}
