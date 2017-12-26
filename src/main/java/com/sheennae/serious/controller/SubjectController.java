package com.sheennae.serious.controller;

import com.sheennae.serious.dao.SubjectRepository;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.subject.SubjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping(value = "/{yyyyMMdd}", method = RequestMethod.POST)
    public @ResponseBody SubjectModel getSubjectByDate(@PathVariable("yyyyMMdd" ) String date) {

        Optional<SubjectModel> subject = subjectRepository.findByCreatedAt(date);

        return subject.get();

    }


    @RequestMapping("")
    public @ResponseBody BaseListModel<SubjectModel> getSubjects(
            @RequestParam(name = "cursor", required = false, defaultValue = "0") String cursor) {

        List<SubjectModel> datas = subjectRepository.findAll();
        BaseListModel<SubjectModel> listModel = new BaseListModel<>();
        listModel.setCursor(0);
        listModel.setCount(datas.size());
        listModel.setDatas(datas);
        return listModel;

    }
}
