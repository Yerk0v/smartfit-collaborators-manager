package com.smartfit.app.smartfitmanager.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartfit.app.smartfitmanager.Entity.Insumos;
import com.smartfit.app.smartfitmanager.Repository.InsumoRepo;

@Service
public class InsumoServicio {
    
    @Autowired
    private InsumoRepo insumoRepo;

    public List<Insumos> listAll() {
        return insumoRepo.findAll();
    }

    public void save(Insumos insumo) {
        insumoRepo.save(insumo);
    }

    public Insumos get(Long id) {
        return insumoRepo.findById(id).get();
    }

    public void delete(Long id) {
        insumoRepo.deleteById(id);
    }
}
