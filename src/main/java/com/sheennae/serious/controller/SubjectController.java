package com.sheennae.serious.controller;

import com.sheennae.serious.dao.SubjectRepository;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.subject.SubjectModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "SubjectController", description = "Operations pertaining to subject in Serious application")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @ApiOperation(value = "Get the subject information that correspond date", response = SubjectModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 404, message = "NOT_FOUND : doesn't exist subject corresponding date"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{yyyyMMdd}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody SubjectModel getSubjectByDate(@PathVariable("yyyyMMdd" ) String date,
                                                       @RequestHeader String uuid) {

        //todo
        //check uuid

        Optional<SubjectModel> subject = subjectRepository.findByCreatedAt(date);

        return subject.get();

    }

    @ApiOperation(value = "Get the subjects' list ", response = BaseListModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED : doesn't exist or wrong format uuid in header"),
            @ApiResponse(code = 404, message = "NOT_FOUND : doesn't exist any subject"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody BaseListModel<SubjectModel> getSubjectList(
            @RequestParam(name = "cursor", required = false, defaultValue = "0") String cursor,
            @RequestHeader String uuid) {

        List<SubjectModel> datas = subjectRepository.findAll();

        BaseListModel<SubjectModel> listModel = new BaseListModel<>();
        listModel.setCursor(Integer.parseInt(cursor));
        listModel.setCount(datas.size());
        listModel.setDatas(datas);

        return listModel;

    }
}
