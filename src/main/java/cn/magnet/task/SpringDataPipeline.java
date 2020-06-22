package cn.magnet.task;

import cn.magnet.pojo.MagnetLink;
import cn.magnet.service.MagnetLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private MagnetLinkService magnetLinkService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        MagnetLink magnetLink = resultItems.get("magnetLink");

        if (magnetLink != null) {
            this.magnetLinkService.save(magnetLink);
        }
    }
}
