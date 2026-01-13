package service;
import javafx.event.ActionEvent;
import model.dto.LogingInfoDTO;

public interface LogingService {
    void getUser(LogingInfoDTO logingInfoDTO, ActionEvent event);
}
