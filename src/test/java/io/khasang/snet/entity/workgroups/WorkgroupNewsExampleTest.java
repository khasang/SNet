package io.khasang.snet.entity.workgroups;

import io.khasang.snet.config.AppContext;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.workgroups.WorkgroupDAO;
import io.khasang.snet.dao.workgroups.WorkgroupNewsDAO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppContext.class, WebConfig.class, HibernateConfig.class})
public class WorkgroupNewsExampleTest {

    @Autowired
    WorkgroupDAO workgroupDAO;

    @Autowired
    WorkgroupNewsDAO workgroupNewsDAO;

    @Test
    @Ignore
    public void addSimpleWorkgroupNews(){
        Workgroup workgroup = new Workgroup();
        workgroup.setTitle("IT Department");
        workgroup.setDescription("Essentially, the IT department is a collection of persons who are experts when it comes to electronic communications of all kinds.");
        workgroup.setHeadWorkgroupId(0);
        workgroup.setWorkgroupType(WorkgroupType.DEPARTMENT);
        //workgroup.setId(77);

        workgroupDAO.addWorkgroup(workgroup);

        List<Workgroup> workgroupList=  new ArrayList<>();
        workgroupList=workgroupDAO.getAllDepartments();
        long wgId=0;

        for (Workgroup wg: workgroupList) {
            if (wg.getTitle().equals(workgroup.getTitle())){
                wgId= wg.getId();
            }
        }


        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        WorkgroupNews workgroupNews = new WorkgroupNews();
        workgroupNews.setWorkgroupId(wgId);
        workgroupNews.setDescription("Южнокорейская компания NCSoft огласила сроки проведения закрытого бета-тестирования многопользовательской ролевой игры Lineage Eternal: Twilight Resistance. ЗБТ начнётся 30 ноября и продлится до 4 декабря. Прямо сейчас пользователи с корейским аккаунтом на официальном сайте проекта могут зарегистрироваться на закрытую «бету». О том, станете ли вы участником грядущего теста, станет известно 24 ноября — эта информация отобразится в вашей учётной записи. Также разработчики опубликовали минимальные системные требования MMORPG для разрешения 1024 x 768.");
        workgroupNews.setTitle("Закрытый бета-тест Lineage Eternal стартует в конце ноября");
        workgroupNews.setNewsDate(date.format(new Date()));
        workgroupNewsDAO.addWorkgroupNews(workgroupNews);



        SimpleDateFormat date2 = new SimpleDateFormat("dd.MM.yyyy");
        WorkgroupNews workgroupNews2 = new WorkgroupNews();
        workgroupNews2.setWorkgroupId(wgId);
        workgroupNews2.setDescription("Более одного миллиона владельцев мобильных устройств на базе Android, сами того не зная, загрузили троянца Android.MulDrop.924, который без ведома пользователей скачивает приложения и предлагает их установить, а также показывает навязчивую рекламу.\n" +
                "Как выяснили эксперты по информационной безопасности компании «Доктор Веб», вредоносная программа содержится в каталоге Google Play и распространяется в виде приложения с именем «Multiple Accounts: 2 Accounts». Данное приложение позволяет одновременно задействовать несколько аккаунтов в играх и другом ПО, установленном на смартфоне или планшете жертвы. Казалось бы, с виду вещь вполне себе безобидная. Подвох же кроется в том, что разработчик программы ничего не упомянул о её скрытых вредоносных функциях.");
        workgroupNews2.setTitle("Более миллиона пользователей Android стали жертвами троянца из Google Play");
        workgroupNews2.setNewsDate(date2.format(new Date()));
        workgroupNewsDAO.addWorkgroupNews(workgroupNews2);


        SimpleDateFormat date3 = new SimpleDateFormat("dd.MM.yyyy");
        WorkgroupNews workgroupNews3 = new WorkgroupNews();
        workgroupNews3.setWorkgroupId(wgId);
        workgroupNews3.setDescription("На протяжении уже двух лет Microsoft предлагает бесплатный офисный пакет для устройств на базе iOS и Android — лишь на большие устройства накладываются некоторые ограничения. Теперь, когда на хромбуках начала появляться возможность запускать приложения для Android, те же ограничения распространяются и на ноутбуки под управлением Chrome OS. Как и в случае с iPad Pro, на хромбуках с экраном диагональю больше 10,1 дюйма офисным пакетом редмондской компании нельзя будет пользоваться бесплатно.\n"+
                "Это ограничение распространяется на большинство хромбуков, присутствующих сейчас на рынке — исключение составляют лишь несколько моделей вроде Chromebook Flip от ASUS, на котором благодаря его 10,1-дюймового экрану Microsoft Office можно будет использовать бесплатно. «Google Play на Chrome OS находится в стадии бета-тестирования, и мы объединяемся с Google для принесения лучшего опыта для пользователей хромбуков и планируем открыть доступ к приложениям на всех совместимых устройствах к моменту общей доступности, — заявил представитель Microsoft изданию 9to5Google. — На устройствах больше 10,1 дюйма для разблокировки возможности создавать, редактировать и печатать документы понадобится подписка Office 365»."
        );
        workgroupNews3.setTitle("Microsoft Office будет требовать наличия подписки на большинстве хромбуков");
        workgroupNews3.setNewsDate(date3.format(new Date()));
        workgroupNewsDAO.addWorkgroupNews(workgroupNews3);

    }

}
