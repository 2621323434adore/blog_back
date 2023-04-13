package org.xirang.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xirang.Entity.Label;
import org.xirang.Service.Impl.LabelServiceImpl;
import org.xirang.Utils.ResultCode;
import org.xirang.Utils.ResultObject;

import java.util.List;

@RestController
public class LabelController {
    @Autowired
    LabelServiceImpl labelService;

    /**
    * 获取所有标签
    * */
    @RequestMapping("/getLabels")
    public ResultObject getLabelList() {
        ResultObject object = new ResultObject();
        List<Label> labelList = labelService.getArticleById();
        object.setMsg("请求成功").setData(labelList).setResultCode(ResultCode.SUCCESS);
        return object;
    }

    /**
     * 根据标签内容对标签进行检索
     * */
    @RequestMapping("/searchLabel")
    public ResultObject searchLabel(@RequestParam String name) {
        ResultObject object = new ResultObject();
        List<Label> labels = labelService.searchLabel(name);
        object.setMsg("请求成功").setData(labels).setResultCode(ResultCode.SUCCESS);
        return object;
    }

    /**
     * 创建新标签
     **/
    @RequestMapping("/addLabel")
    public ResultObject addLabel(@RequestParam String name) {
        ResultObject object = new ResultObject();
        Label label = new Label(0, name, name.toUpperCase());
        boolean status = labelService.saveLabel(label);
        if (status) {
            object.setResultCode(ResultCode.SUCCESS).setMsg("新增标签成功！").setData("Y");
        } else {
            object.setResultCode(ResultCode.FAILED).setMsg("新增标签失败！").setData("N");
        }
        return object;
    }


    /**
     * 删除标签
     * **/
    @RequestMapping("/deleteLabel")
    public ResultObject deleteLabel(@RequestParam int id) {
        ResultObject object = new ResultObject();
        int i = labelService.deleteArticle(id);
        if (i == 0) {
            object.setResultCode(ResultCode.FAILED).setMsg("删除标签失败！").setData("N");
        } else {
            object.setResultCode(ResultCode.SUCCESS).setMsg("删除标签成功！").setData("Y");
        }
        return object;
    }
}
