package vn.iotstar.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Store;
import vn.iotstar.repository.StoreRepository;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepo;

	public <S extends Store> S save(S entity) {
		return storeRepo.save(entity);
	}

	public <S extends Store> S insert(S entity) {
		return storeRepo.insert(entity);
	}

	public List<Store> findAll() {
		return storeRepo.findAll();
	}

	public Optional<Store> findById(String id) {
		return storeRepo.findById(id);
	}

	public boolean existsById(String id) {
		return storeRepo.existsById(id);
	}

	public long count() {
		return storeRepo.count();
	}

	public void delete(Store entity) {
		storeRepo.delete(entity);
	}

	public void deleteAll() {
		storeRepo.deleteAll();
	}
	
}
