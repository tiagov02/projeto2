package com.example.springwebmvc.ModelClasses;

import java.util.ArrayList;
import java.util.List;

public class ModelFatura {
    private float valTotal;
    private List<ModelLinhaFatura> linhaFat;

    public ModelFatura() {
        this.linhaFat = new ArrayList<>();
    }

    public float getValTotal() {
        return valTotal;
    }

    public void setValTotal(float valTotal) {
        this.valTotal = valTotal;
    }

    public List<ModelLinhaFatura> getLinhaFat() {
        return linhaFat;
    }

    public void setLinhaFat(List<ModelLinhaFatura> linhaFat) {
        this.linhaFat = linhaFat;
    }
}
