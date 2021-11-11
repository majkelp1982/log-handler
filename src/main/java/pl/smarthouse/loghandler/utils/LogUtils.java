package pl.smarthouse.loghandler.utils;

import pl.smarthouse.loghandler.model.ErrorDto;
import pl.smarthouse.loghandler.model.InfoDto;

public class LogUtils {

  public static ErrorDto error(final String moduleName, final String message) {
    return ErrorDto.builder().moduleName(moduleName).message(message).build();
  }

  public static InfoDto info(final String moduleName, final String message) {
    return InfoDto.builder().moduleName(moduleName).message(message).build();
  }
}
