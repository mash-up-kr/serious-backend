package com.sheennae.serious.controller;

import com.sheennae.serious.dao.SubjectRepository;
import com.sheennae.serious.dto.SubjectDTO;
import com.sheennae.serious.exception.BadRequestException;
import com.sheennae.serious.exception.DuplicatedException;
import com.sheennae.serious.exception.NotFoundException;
import com.sheennae.serious.model.BaseListModel;
import com.sheennae.serious.model.subject.SubjectModel;
import com.sheennae.serious.model.subject.command.SubjectCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "/subject")
@Api(value = "SubjectController", description = "Subject Controller")
public class SubjectController {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper = new ModelMapper();



    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }




    @ApiOperation(value = "Get the subject information that correspond date", response = SubjectModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "/{yyyyMMdd}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody SubjectDTO getSubjectByDate(@PathVariable("yyyyMMdd") String date) {
        return subjectRepository
                .findByPublishedAt(date)
                .map(item -> modelMapper.map(item, SubjectDTO.class))
                .orElseThrow(() -> new NotFoundException(String.format("There's no subject in %s", date)));
    }



    @ApiOperation(value = "Get the subjects' list ", response = BaseListModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody BaseListModel<SubjectDTO> getSubjectList(@RequestParam int year, @RequestParam int month) {
        if (year < 2018 || year > 2050) {
            throw new BadRequestException("Please request current year. Not past, Not Future");
        }

        if (month < 1 || month > 12) {
            throw new BadRequestException("Please request between January and December");
        }

        List<SubjectModel> datas = subjectRepository.findByMonth(year, month);
        Type type = new TypeToken<List<SubjectModel>>(){}.getType();

        BaseListModel<SubjectDTO> listModel = new BaseListModel<>();
        listModel.setCount(datas.size());
        listModel.setDatas(modelMapper.map(datas, type));
        return listModel;
    }



    @ApiOperation(value = "Post subject")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 409, message = "DUPLICATED"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody void postSubject(@RequestHeader String adminKey, @RequestBody SubjectCommand command) {
        if (adminKey == null || !"serious-admin".equals(adminKey)) {
            throw new BadRequestException("Please input the admin key");
        }

        String title = command.getTitle();
        if (title == null || title.trim().length() == 0) {
            throw new BadRequestException("Please input the title");
        }

        LocalDateTime publishedAt = command.getPublishedAt();
        if (publishedAt == null) {
            throw new BadRequestException("Please input the publishedAt");
        }

        if (subjectRepository.findToday(command.getPublishedAt().toString()).isPresent()) {
            throw new DuplicatedException("Today subject already published.");
        }

        SubjectModel subject = new SubjectModel();
        subject.setTitle(title);
        subject.setPublishedAt(LocalDateTime.of(publishedAt.toLocalDate(), LocalTime.MIN));


        subjectRepository.save(subject);
    }

}
