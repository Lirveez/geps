package mirea.geps.web.controller;

import com.google.common.collect.Sets;
import mirea.geps.core.dto.Competition;
import mirea.geps.core.dto.ControlForm;
import mirea.geps.core.dto.Discipline;
import mirea.geps.core.dto.EducationalPlan;
import mirea.geps.core.service.db.EducationalPlanRepositoryCustom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EducationalPlanRepositoryCustom repositoryCustom;

    @Before
    public void setUp() throws Exception {
        Competition competition1 = new Competition("compCode1", "desc1", "type1");
        Competition competition2 = new Competition("compCode2", "desc2", "type2");
        ControlForm controlForm = new ControlForm("controlForm");
        Discipline discipline1 = new Discipline(
                "discCode1",
                "name1",
                Sets.newHashSet(competition1, competition2),
                Sets.newHashSet(controlForm),
                "exam_units1",
                "lec_hours1",
                "lab_hours1",
                "prc_hours1",
                "self_hours1",
                "1"

        );
        Discipline discipline2 = new Discipline(
                "discCode2",
                "name2",
                Sets.newHashSet(competition2),
                Sets.newHashSet(controlForm),
                "exam_units2",
                "lec_hours2",
                "lab_hours2",
                "prc_hours2",
                "self_hours2",
                "2"

        );
        EducationalPlan plan = new EducationalPlan(
                "customName",
                "trDirection",
                "profile",
                "form",
                "plan",
                "department",
                Sets.newHashSet(discipline1, discipline2)
        );
        Optional<EducationalPlan> optionalPlan = Optional.of(plan);
        when(repositoryCustom.findPlanByCustomName("plan")).thenReturn(optionalPlan);
    }

    @Test
    public void shouldReturnEducationalPlan() throws Exception {
        String request = "{\n" +
                "  \"name\" : \"plan\"\n" +
                "}";
        String response = "{\n" +
                "  \"customName\": \"customName\",\n" +
                "  \"trDirection\": \"trDirection\",\n" +
                "  \"profile\": \"profile\",\n" +
                "  \"form\": \"form\",\n" +
                "  \"plan\": \"plan\",\n" +
                "  \"department\": \"department\",\n" +
                "  \"disciplines\": [\n" +
                "    {\n" +
                "      \"code\": \"discCode1\",\n" +
                "      \"name\": \"name1\",\n" +
                "      \"competitions\": [\n" +
                "        {\n" +
                "          \"code\": \"compCode1\",\n" +
                "          \"description\": \"desc1\",\n" +
                "          \"type\": \"type1\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"code\": \"compCode2\",\n" +
                "          \"description\": \"desc2\",\n" +
                "          \"type\": \"type2\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"controlForms\": [\n" +
                "        {\n" +
                "          \"name\": \"controlForm\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"exam_units\": \"exam_units1\",\n" +
                "      \"lec_hours\": \"lec_hours1\",\n" +
                "      \"lab_hours\": \"lab_hours1\",\n" +
                "      \"prc_hours\": \"prc_hours1\",\n" +
                "      \"self_hours\": \"self_hours1\",\n" +
                "      \"course\": \"1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"discCode2\",\n" +
                "      \"name\": \"name2\",\n" +
                "      \"competitions\": [\n" +
                "        {\n" +
                "          \"code\": \"compCode2\",\n" +
                "          \"description\": \"desc2\",\n" +
                "          \"type\": \"type2\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"controlForms\": [\n" +
                "        {\n" +
                "          \"name\": \"controlForm\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"exam_units\": \"exam_units2\",\n" +
                "      \"lec_hours\": \"lec_hours2\",\n" +
                "      \"lab_hours\": \"lab_hours2\",\n" +
                "      \"prc_hours\": \"prc_hours2\",\n" +
                "      \"self_hours\": \"self_hours2\",\n" +
                "      \"course\": \"2\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/eps/file/get-plan").contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    public void shouldSaveEducationalPlan() throws Exception {
        String request = "{\n" +
                "  \"customName\": \"customName\",\n" +
                "  \"trDirection\": \"trDirection\",\n" +
                "  \"profile\": \"profile\",\n" +
                "  \"form\": \"form\",\n" +
                "  \"plan\": \"plan\",\n" +
                "  \"department\": \"department\",\n" +
                "  \"disciplines\": [\n" +
                "    {\n" +
                "      \"code\": \"discCode1\",\n" +
                "      \"name\": \"name1\",\n" +
                "      \"competitions\": [\n" +
                "        {\n" +
                "          \"code\": \"compCode1\",\n" +
                "          \"description\": \"desc1\",\n" +
                "          \"type\": \"type1\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"code\": \"compCode2\",\n" +
                "          \"description\": \"desc2\",\n" +
                "          \"type\": \"type2\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"controlForms\": [\n" +
                "        {\n" +
                "          \"name\": \"controlForm\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"exam_units\": \"exam_units1\",\n" +
                "      \"lec_hours\": \"lec_hours1\",\n" +
                "      \"lab_hours\": \"lab_hours1\",\n" +
                "      \"prc_hours\": \"prc_hours1\",\n" +
                "      \"self_hours\": \"self_hours1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"discCode2\",\n" +
                "      \"name\": \"name2\",\n" +
                "      \"competitions\": [\n" +
                "        {\n" +
                "          \"code\": \"compCode2\",\n" +
                "          \"description\": \"desc2\",\n" +
                "          \"type\": \"type2\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"controlForms\": [\n" +
                "        {\n" +
                "          \"name\": \"controlForm\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"exam_units\": \"exam_units2\",\n" +
                "      \"lec_hours\": \"lec_hours2\",\n" +
                "      \"lab_hours\": \"lab_hours2\",\n" +
                "      \"prc_hours\": \"prc_hours2\",\n" +
                "      \"self_hours\": \"self_hours2\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/eps/file/upload-plan").contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk());


    }
}