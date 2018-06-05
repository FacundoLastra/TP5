package com.tp5.tp5.utilitys;

import java.util.List;

public class CreateResponse<T,TE> {

    public List<TE> getInstance(List <T> process , List<TE> retrunTE) throws Exception {
        process.forEach(p->retrunTE.add((TE) p));
        return retrunTE;
    }
}
