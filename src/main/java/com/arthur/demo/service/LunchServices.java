package com.arthur.demo.service;

import com.arthur.demo.model.LunchAtWork;
import com.arthur.demo.repository.LunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class LunchServices {

    @Autowired
    LunchRepository lunchRepository;

    //récuperer tous les repas
    public List<LunchAtWork> getLunch (){
        return this.lunchRepository.findAll();
    }

    //ajouter un repas à la base de données
    public void addLunch(LunchAtWork launch){
        this.lunchRepository.save(launch);
    }

    //Supprimer un repas
    public void deleteLunch(String id){
        if(lunchRepository.findById(id) != null){
            this.lunchRepository.deleteById(id);
        }
    }

    //modifier un repas
    public LunchAtWork updateLunch(LunchAtWork lunch){

        LunchAtWork lunchToUpdate = lunch;
        if (lunch.getId() != null){

            if(this.lunchRepository.findById(lunch.getId()).isPresent()) {
                lunchToUpdate = lunchRepository.findById(lunch.getId()).get();
                lunchToUpdate.setPlat(lunch.getPlat());
                lunchToUpdate.setDescription(lunch.getDescription());
                lunchToUpdate.setComposition(lunch.getComposition());
                lunchToUpdate.setDate(lunch.getDate());
                lunchToUpdate.setPerson(lunch.getPerson());
                lunchToUpdate.setImage(lunch.getImage());
                lunchToUpdate = lunch;
                this.lunchRepository.save(lunch);
            }
        }
        this.lunchRepository.save(lunch);
        return  lunchToUpdate;

    }

    //verifie l'existence d'un lunch dans la bd
    public boolean existLunch(String id){
        return (this.lunchRepository.findById(id)).isPresent();
    }

    public List<LunchAtWork> findByName(String plat){
        List<LunchAtWork> allLunches = this.lunchRepository.findAll();
        List<LunchAtWork> lunchesByPlat = new ArrayList<>();

        for (int i=0; i<allLunches.size(); i++){
            if(allLunches.get(i).getPlat().equals(plat)){
                lunchesByPlat.add(allLunches.get(i));
            }
        }
        return lunchesByPlat;
    }

    //Récuperer les repas de la personne saisie
    public List<LunchAtWork> findByPerson(String firstname, String lastname){
        List<LunchAtWork> allLunches = this.lunchRepository.findAll();
        List<LunchAtWork> lunchesByPerson = new ArrayList<>();

        for (int i=0; i<allLunches.size(); i++){
            if(allLunches.get(i).getPerson().getFirstname().equals(firstname) && allLunches.get(i).getPerson().getLastname().equals(lastname)  ){
                lunchesByPerson.add(allLunches.get(i));
            }
        }
        return lunchesByPerson;
    }
    public LunchAtWork displayLunch(String id) {
        return this.lunchRepository.findById(id).get();
    }

    //récupérer toutes les dates
    public List<String> getLunchDate() {
        List<String> dates = new ArrayList<String>();
        for (int i=0; i<this.lunchRepository.findAll().size(); i++){
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            dates.add(df.format(this.lunchRepository.findAll().get(i).getDate()));
        }
        return dates;
    }

}
