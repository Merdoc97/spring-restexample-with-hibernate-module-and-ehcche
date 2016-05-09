package com.example;

import com.example.model.ItemAttributes;
import com.example.model.Items;
import com.example.service.GeneralService;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.runners.Parameterized.Parameters;

/**
 * Parametrized test with Spring
 * testing general service
 */
@RunWith(Parameterized.class)
@SpringApplicationConfiguration(classes = SpringRestWithCacheApplication.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@WebAppConfiguration
public class SpringRestWithCacheApplicationTests {

    private ApplicationContext context;
    private GeneralService generalService;
    private String beanName;

    public SpringRestWithCacheApplicationTests(String beanName) {
        this.beanName = beanName;
        context = new GenericXmlApplicationContext("appconfigTest.xml");
        this.generalService = (GeneralService) context.getBean(beanName);
    }

    @Parameters
    public static List<String> getBeans() {
        return Arrays.asList("itemService", "itemAttribute");
    }

    @Test
    public void getAllGeneralServiceParametrized() {
        Assert.assertNotNull(generalService.getAll());
        Assert.assertTrue(generalService.getAll().size() > 0);
    }

    @Test
    public void getByIdtestGeneralServiceParametrized() {
        Assert.assertNotNull(generalService.getById(1));
    }

    @Test
    public void deleteGeneralServiceParametrized() {
        int sizeBefore = generalService.getAll().size();
        generalService.delete(1);
        int sizeAfter = generalService.getAll().size();
        Assert.assertNotSame(sizeBefore, sizeAfter);
        Assert.assertTrue(sizeBefore > sizeAfter);
    }

    @Test
    public void saveTest() throws NotFoundException {
        if (beanName.equals("itemService")) {
            Items items = (Items) generalService.getAll().get(0);
            int sizeBefore = generalService.getAll().size();
            items.setId(null);
            generalService.save(items);
            int sizeAfter = generalService.getAll().size();
            Assert.assertNotSame(sizeBefore, sizeAfter);
            Assert.assertTrue(sizeAfter > sizeBefore);
        }
        if (beanName.equals("itemAttribute")) {
            ItemAttributes attributes = (ItemAttributes) generalService.getAll().get(0);
            int sizeBefore = generalService.getAll().size();
            attributes.setId(null);
            generalService.save(attributes);
            int sizeAfter = generalService.getAll().size();
            Assert.assertNotSame(sizeBefore, sizeAfter);
            Assert.assertTrue(sizeAfter > sizeBefore);
        }
    }

    @Test
    public void updateTest() throws NotFoundException {
        if (beanName.equals("itemService")) {
            //pre condition
            Items items = (Items) generalService.getAll().get(0);
            int id=items.getId();
            String updateValue="new Value";
            items.setName(updateValue);
            generalService.update(items);
            items= (Items) generalService.getById(id);
            //test
            Assert.assertEquals("check it updates",items.getName(),updateValue);
        }
        if (beanName.equals("itemAttribute")) {
            //pre condition
            ItemAttributes attributes = (ItemAttributes) generalService.getAll().get(0);
            int id=attributes.getId();
            String updateValue="new Value";
            attributes.setValue(updateValue);
            generalService.update(attributes);
            attributes= (ItemAttributes) generalService.getById(id);
            //test
            Assert.assertEquals("check for update",attributes.getValue(),updateValue);
        }
    }
}
