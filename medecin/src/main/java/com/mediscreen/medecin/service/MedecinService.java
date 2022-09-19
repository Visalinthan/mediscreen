package com.mediscreen.medecin.service;

import com.mediscreen.medecin.model.Medecin;
import com.mediscreen.medecin.repository.MedecinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinService {
    private MedecinRepository medecinRepository;

    public MedecinService(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    /**
     * Recherche une liste de medecin avec la méthode findAll de l'interface medecinRepository
     * @return une liste de medecin
     */
    public List<Medecin> list(){return this.medecinRepository.findAll();}

    /**
     * Recherche un medecin en fonction de l'id dans le paramètre avec la méthode findAll de medecinRepository
     * @param id
     * @return medecin
     */
    public Optional<Medecin> findById(Long id){return this.medecinRepository.findById(id);}

    /**
     * Sauvergarde d'un medecin avec la méthode save de medecinRepository
     * @param medecin
     * @return une sauvegarde medecin
     */
    public Medecin save(Medecin medecin){
        return this.medecinRepository.save(medecin);
    }


    /**
     * Suppression d'un medecin en récupérant l'id dans le paramètre avec la méthode deleteById de medecinRepository
     * @param id
     */
    public void deleteById(Long id){
        this.medecinRepository.deleteById(id);
    }
}
