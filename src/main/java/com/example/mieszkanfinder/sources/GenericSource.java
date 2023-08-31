package com.example.mieszkanfinder.sources;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;

import java.util.List;

public abstract class GenericSource {
    public abstract List<GenericMieszkanieModel> getMieszkaniesData();
}
