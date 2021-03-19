package me.seunghak.restapiwithspring;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import me.seunghak.restapiwithspring.events.Event;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class EventTest {
    @Test
    public void builder() throws Exception {
        Event event = Event.builder()
                .name("Inflearn Srping REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() throws Exception {
        String name = "Event";
        String description = "Spring";

        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }

    private Object[] parametersForTestoffline() {
        return new Object[] [] {
                new Object[]{"강남",true},
                new Object[]{null,false},
                new Object[]{"   ",false},
        };
    }
    private Object[] parametersForTestFree() {
        return new Object[] [] {
                new Object[]{0,0,true},
                new Object[]{100,0,false},
                new Object[]{0,100,false},
                new Object[]{100,200,false}
        };
    }

    @Test
    @Parameters
    public void testFree(int basePrice, int maxPrice, boolean isFree) throws Exception {
        // given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
        // when
        event.update();

        // then
        assertThat(event.isFree()).isEqualTo(isFree);
    }



    @Test
    @Parameters
    public void testoffline(String location, boolean isOffline) throws Exception {
        // given
        Event event = Event.builder()
                .location(location)
                .build();
        // when
        event.update();

        // then
        assertThat(event.isOffline()).isEqualTo(isOffline);
    }
}
