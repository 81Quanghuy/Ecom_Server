package vn.iotstar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;

	public <S extends Category> S save(S entity) {
		return categoryRepo.save(entity);
	}

	public <S extends Category> S insert(S entity) {
		return categoryRepo.insert(entity);
	}

	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	public Optional<Category> findById(String id) {
		return categoryRepo.findById(id);
	}

	public long count() {
		return categoryRepo.count();
	}

	public void deleteById(String id) {
		categoryRepo.deleteById(id);
	}

	public void deleteAll() {
		categoryRepo.deleteAll();
	}
	
}
