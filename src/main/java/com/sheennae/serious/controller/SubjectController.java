package com.sheennae.serious.controller;

import com.sheennae.serious.dao.SubjectRepository;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.subject.SubjectModel;
import com.sheennae.serious.model.subject.command.SubjectCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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

    @ApiOperation(value = "Post subject")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "SUCCESS", response = Void.class),
            @ApiResponse(code = 402, message = "BAD_REQUEST"),
            @ApiResponse(code = 402, message = "DUPLICATED"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody void postSubject(@RequestHeader String adminKey, @RequestBody SubjectCommand command, HttpServletResponse response) {
        System.out.println("post subject called");
        if (adminKey == null || !"serious-admin".equals(adminKey)) {
            response.setStatus(400);
            return;
        }

        System.out.println("admin check complete");
        String title = command.getTitle();
        if (title == null || title.trim().length() == 0) {
            response.setStatus(400);
            return;
        }

        System.out.println("title check complete");
        if (subjectRepository.findToday().isPresent()) {
            System.out.println("hello world!");
            response.setStatus(402);
            return;
        }

        System.out.println("today subject check complete");
        SubjectModel subject = new SubjectModel();
        subject.setTitle(title);
        // published at 은 admin 페이지에서 설정한다.
        subject.setPublishedAt(LocalDateTime.now());


        subjectRepository.save(subject);
        response.setStatus(204);
    }
}
