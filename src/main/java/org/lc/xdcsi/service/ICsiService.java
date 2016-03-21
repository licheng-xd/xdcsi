package org.lc.xdcsi.service;

import org.lc.xdcsi.dao.domain.CsiVcard;
import java.util.List;

/**
 * Created by lc on 16/3/20.
 */
public interface ICsiService {
    int addVcard(CsiVcard vcard);

    CsiVcard getVcard(String name);

    List<CsiVcard> getVcardAll();

    int remove(String name);

    List<CsiVcard> search(String key);
}
