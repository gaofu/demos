package gao.fu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import gao.fu.model.MessageScale;
import gao.fu.service.MessageScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageScaleController {

    @Autowired
    private MessageScaleService messageScaleService;

    @RequestMapping(value = "/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(MessageScale.WithDate.class)
    public List<MessageScale> getMessageScaleTrend(@PathVariable("token") String token,
                                                   @RequestParam("dateType") String dateType,
                                                   @RequestParam("begin") String begin,
                                                   @RequestParam("end") String end) {

        return messageScaleService.getMessageScaleTrend(token, dateType, begin, end);
    }

    @RequestMapping(value = "/top", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(MessageScale.WithToken.class)
    public List<MessageScale> getTopMessageScale(@RequestParam("dateType") String dateType,
                                                 @RequestParam("date") String date,
                                                 @RequestParam(value = "top", required = false, defaultValue = "10") int top) {
        return messageScaleService.getTopMessageScale(dateType, date, top);
    }

}
