package com.baseproject.parameterization.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baseproject.parameterization.model.Funcionalidade;
import com.baseproject.parameterization.model.FuncionalidadeRepository;
import com.baseproject.parameterization.model.Parametro;
import com.baseproject.parameterization.model.ParametroRepository;
import com.baseproject.util.PageableUtil;
import com.baseproject.util.ServiceUtil;

@Service
@Transactional
public class ParametroService {

	@Autowired
	private ParametroRepository repository;

	@Autowired
	private FuncionalidadeRepository funcRepository;

	public Parametro find(String uuid) {
		return repository.findByUuid(uuid).orElse(new Parametro());
	}

	public void delete(String uuid) {
		Parametro entity = repository.findByUuid(uuid).orElseThrow();
		repository.delete(entity);
	}

	public void save(Parametro prm) {
		if (ServiceUtil.notEmpty(prm.getUuid())) {
			Parametro entity = repository.findByUuid(prm.getUuid()).orElseThrow();
			entity.setDescricao(prm.getDescricao());
			entity.setFuncionalidade(prm.getFuncionalidade());
			repository.save(entity);
		} else {
			prm.setUuid(ServiceUtil.generateUuid());
			repository.save(prm);
		}
	}

	public List<Parametro> list(Optional<String> fieldName, Optional<String> fieldValue) {
		if (!fieldName.isPresent() || !fieldValue.isPresent()) {
			return repository.findAll(PageableUtil.getPageable("nome")).toList();
		} else if (fieldName.get().equals("nome")) {
			return repository.findByNomeContainingIgnoreCase(fieldValue.get().trim(), PageableUtil.getPageable());
		} else if (fieldName.get().equals("funcionalidade") && !fieldValue.get().isEmpty()) {
			Long idFunc = Long.parseLong(fieldValue.get().trim());
			Funcionalidade func = funcRepository.findById(idFunc).orElseThrow();
			return repository.findByFuncionalidade(func);
		} else {
			return repository.findAll(PageableUtil.getPageable("nome")).toList();
		}
	}

	public List<Funcionalidade> funcionalidades() {
		return funcRepository.findAll();
	}

}
