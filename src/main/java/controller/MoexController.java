package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.trade.data.da.entity.ch.MoexDto;

import java.util.List;

@RestController
@RequestMapping("/shares-consumer")
public class MoexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoexController.class);

    @PostMapping
    public Integer getTopSharesByDay(@RequestBody List<MoexDto> moexDto) {
        LOGGER.info(moexDto.toString());
        return HttpStatus.I_AM_A_TEAPOT.value();
    }
}
