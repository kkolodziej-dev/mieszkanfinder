package com.example.mieszkanfinder.logic;
import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.sources.OLXSource;
import org.springframework.stereotype.Component;
import java.util.*;

import static com.example.mieszkanfinder.helpers.GlobalCredentialsStore.TIME_NOW;

@Component
public class GetMieszkaniesLogicManipulator {

    public List<GenericMieszkanieModel> getMieszkanies() {

        List<GenericMieszkanieModel> allMieszkaniesList = OLXSource.getMieszkaniesData();

        return allMieszkaniesList.stream().filter(x -> x.getDataDodaniaOgloszenia().startsWith(TIME_NOW)).toList();
    }
}
