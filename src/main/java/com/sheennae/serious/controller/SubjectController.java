package com.sheennae.serious.controller;

import com.sheennae.serious.dao.SubjectRepository;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.subject.SubjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping("/today")
    public @ResponseBody SubjectModel getTodaySubject() {
        List<SubjectModel> datas = subjectRepository.findAll();
        return datas.get(datas.size() - 1);
    }


    @RequestMapping("")
    public @ResponseBody BaseListModel<SubjectModel> getSubjects(@RequestParam(name = "cursor", required = false, defaultValue = "0")
                                                                             String cursor) {

        List<SubjectModel> datas = subjectRepository.findAll();
        BaseListModel<SubjectModel> listModel = new BaseListModel<>();
        listModel.setCursor(0);
        listModel.setCount(datas.size());
        listModel.setDatas(datas);
        return listModel;
    }
}
