package com.sismas.erp.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named("contract")
@SessionScoped
public class ContractBean implements Serializable {

    private List<SelectItem> contracts;
    private String name = "poseidon";

    public ContractBean() {
    }

    @PostConstruct
    public void init() {
        contracts = new ArrayList<>();
        contracts.add(new SelectItem("apollo", "Apollo"));
        contracts.add(new SelectItem("icarus", "Icarus"));
        //contracts.add(new SelectItem("omega", "Omega"));
        contracts.add(new SelectItem("poseidon", "Poseidon"));
        contracts.add(new SelectItem("ultima", "Ultima"));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public List<SelectItem> getContracts() {
        return contracts;
    }

    public void setContracts(List<SelectItem> contracts) {
        this.contracts = contracts;
    }

}
