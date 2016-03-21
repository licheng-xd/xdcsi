package org.lc.xdcsi.service.impl;

import org.lc.xdcsi.dao.CsiMapper;
import org.lc.xdcsi.dao.domain.CsiVcard;
import org.lc.xdcsi.service.ICsiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lc on 16/3/20.
 */
@Service("csiService")
public class CsiServiceImpl implements ICsiService {

    @Resource
    private CsiMapper mapper;

    @Override public int addVcard(CsiVcard vcard) {
        return mapper.insertVcard(vcard);
    }

    @Override public CsiVcard getVcard(String name) {
        return mapper.getVcard(name);
    }

    @Override public List<CsiVcard> getVcardAll() {
        return mapper.getVcardAll();
    }

    @Override public int remove(String name) {
        return mapper.removeVcard(name);
    }

    @Override public List<CsiVcard> search(String key) {
        return mapper.searchVcard("%" + key + "%");
    }
}
