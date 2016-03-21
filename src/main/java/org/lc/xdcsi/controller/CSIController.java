package org.lc.xdcsi.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.lc.xdcsi.bean.RespBody;
import org.lc.xdcsi.dao.domain.CsiVcard;
import org.lc.xdcsi.service.impl.CsiServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * xdcsi页面接口
 *
 * Created by lc on 16/3/20.
 */

@Controller
//@RequestMapping(value = {"/csi", "/"})
public class CSIController {
    private static final String USERNAME = DigestUtils.md5Hex("xdcsi");

    private static final String PASS = DigestUtils.md5Hex("csi1008");

    private static final String SALT = "niubi";

    private static String COOKIE = DigestUtils.md5Hex(USERNAME + SALT + PASS);

    @Resource
    private CsiServiceImpl csiService;

    @ResponseBody
    @RequestMapping(value = {"/add"}, method = { RequestMethod.POST})
    public RespBody add(@RequestParam String name, @RequestParam String mobile,
        @RequestParam String city, @RequestParam String company,
        @RequestParam String position, @RequestParam String graduation) {
        CsiVcard check = csiService.getVcard(name);
        if (check != null) {
            return RespBody.builder(HttpStatus.BAD_REQUEST).setMsg(name + " already exist.");
        }
        CsiVcard vcard = new CsiVcard();
        vcard.setName(name);
        vcard.setMobile(mobile);
        vcard.setCity(city);
        vcard.setCompany(company);
        vcard.setPos(position);
        vcard.setGraduation(graduation);
        int ret = csiService.addVcard(vcard);
        if (ret > 0) {
            return RespBody.success();
        } else {
            return RespBody.builder(HttpStatus.INTERNAL_SERVER_ERROR).setMsg("db error");
        }
    }

    @ResponseBody
    @RequestMapping(value = {"/remove"}, method = { RequestMethod.POST})
    public RespBody remove(@RequestParam String name) {
        CsiVcard check = csiService.getVcard(name);
        if (check == null) {
            return RespBody.builder(HttpStatus.NOT_FOUND).setMsg(name + " not exist.");
        }
        int ret = csiService.remove(name);
        if (ret > 0) {
            return RespBody.success();
        } else {
            return RespBody.builder(HttpStatus.INTERNAL_SERVER_ERROR).setMsg("db error");
        }
    }

    @ResponseBody
    @RequestMapping(value = {"/all"})
    public RespBody all() {
        List<CsiVcard> vcards = csiService.getVcardAll();
        if (vcards == null || vcards.isEmpty()) {
            return RespBody.builder(HttpStatus.NOT_FOUND);
        }
        return RespBody.success().setObj(vcards);
    }

    @ResponseBody
    @RequestMapping(value = {"/search"}, method = { RequestMethod.POST})
    public RespBody search(@RequestParam String key) {
        List<CsiVcard> vcards = csiService.search(key);
        if (vcards == null || vcards.isEmpty()) {
            return RespBody.builder(HttpStatus.NOT_FOUND);
        }
        return RespBody.success().setObj(vcards);
    }

    @ResponseBody
    @RequestMapping(value = {"/dologin"}, method = { RequestMethod.POST})
    public RespBody login(@RequestParam String username, @RequestParam String pass) {
        if (username.equals(USERNAME) && pass.equals(PASS)) {
            return RespBody.success().setObj(COOKIE);
        } else {
            return RespBody.builder(HttpStatus.FORBIDDEN);
        }
    }

    @ResponseBody
    @RequestMapping(value = {"/cookielogin"}, method = { RequestMethod.POST})
    public RespBody login(@RequestParam String cookie) {
        if (cookie.equals(COOKIE)) {
            return RespBody.success();
        } else {
            return RespBody.builder(HttpStatus.FORBIDDEN);
        }
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("1"));
    }
}
