package mweb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class Upload {
  @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
  public List<String> upload(MultipartFile file) {
    try {
      Tools.getCharsets(file.getBytes());
      String str = new String(file.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return Arrays.asList("abc", "dad", "ccc");
  }
}
