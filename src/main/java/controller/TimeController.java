package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {

    @GetMapping("/clock")
    public String getTimeByTimeZone(@RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city, ModelMap modelMap){
        Date date = new Date(); //Khai bao doi tuong thoi gian
        TimeZone local = TimeZone.getDefault(); //tra ve TimeZone mac dinh cua may chu, mui gio cua dia phuong
        TimeZone locale = TimeZone.getTimeZone(city); //Tra ve mui gio cua thanh pho duoc chi dinh
        //Tinh thoi gian hien tai trong thanh pho duoc chi dinh
        long locale_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
        // Dat lai ngay theo locale_time
        date.setTime(locale_time);

        //Chuyen du lieu ve cho View
        modelMap.addAttribute("city", city);
        modelMap.addAttribute("date", date);
        return "index";
    }
}
/* name: là tên của parametter binding .
   required : là cho biết tham số này có là bắt buộc hay không, nếu = true thì thiếu parametter
   thì request sẽ fail.
   defaultValue: là giá trị mặc định nếu như giá trị của parametter trên URL rỗng.

 */