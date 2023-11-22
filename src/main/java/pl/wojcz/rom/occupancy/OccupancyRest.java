package pl.wojcz.rom.occupancy;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojcz.rom.occupancy.type.OccupancyRequest;
import pl.wojcz.rom.occupancy.type.OccupancyUsage;

import java.util.List;

@RestController
@RequestMapping("/occupancy")
public class OccupancyRest {

    private final OccupancyService service;

    public OccupancyRest(OccupancyService service) {
        this.service = service;
    }

    @PostMapping("/check")
    public List<OccupancyUsage> check(
            @Valid
            @RequestBody
            OccupancyRequest occupancyRequest
    ) {
        return service.check(occupancyRequest.getPremium(), occupancyRequest.getEconomy());
    }
}
