package org.lc.xdcsi.dao;

import org.lc.xdcsi.dao.domain.CsiVcard;

import java.util.List;

/**
 * Created by lc on 16/3/20.
 */
public interface CsiMapper {

    int insertVcard(CsiVcard vcard);

    CsiVcard getVcard(String name);

    List<CsiVcard> getVcardAll();

    int removeVcard(String name);

    List<CsiVcard> searchVcard(String key);

}
