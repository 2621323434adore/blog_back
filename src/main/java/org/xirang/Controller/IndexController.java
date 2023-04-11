package org.xirang.Controller;


import org.springframework.web.bind.annotation.*;
import org.xirang.Utils.ResultCode;
import org.xirang.Utils.ResultObject;

@RestController
public class IndexController {

    @CrossOrigin
    @GetMapping("/get")
    public ResultObject get(@RequestParam String sum){
       ResultObject resultObject =  new ResultObject();
       resultObject.setResultCode(ResultCode.SUCCESS);
       resultObject.setMsg("请求成功");
       resultObject.setData("ok");
      return resultObject;
    }

    @CrossOrigin
    @PostMapping("/post")
    public ResultObject post(@RequestParam String sum, @RequestParam String message){
        ResultObject resultObject =  new ResultObject();
        resultObject.setResultCode(ResultCode.SUCCESS);
        resultObject.setMsg("请求成功");
        resultObject.setData("ok");
        return resultObject;
    }
}
