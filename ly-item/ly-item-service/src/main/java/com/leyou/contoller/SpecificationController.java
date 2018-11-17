package com.leyou.contoller;

import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.SpecParam;
import com.leyou.service.SpecSeervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther:XUJIAN
 * @date=${DATA}21:30
 */
@RestController
@RequestMapping("spec")
public class SpecificationController {
    @Autowired
    private SpecSeervice specSeervice;

    @GetMapping("groups/{id}")
    public ResponseEntity<List<SpecGroup>> querySpecGroups(@PathVariable("id") Long id) {
        List<SpecGroup> specGroupList = specSeervice.querySpecGroups(id);
        if (specGroupList != null && specGroupList.size() > 0) {
            return ResponseEntity.ok(specGroupList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParam(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "generic", required = false) Boolean generic) {
        List<SpecParam> specParamList = specSeervice.querySpecParam(gid, cid, searching, generic);
        if (specParamList != null && specParamList.size() > 0) {
            return ResponseEntity.ok(specParamList);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

