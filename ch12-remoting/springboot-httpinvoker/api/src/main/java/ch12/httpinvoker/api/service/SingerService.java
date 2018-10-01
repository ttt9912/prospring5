package ch12.httpinvoker.api.service;

import ch12.httpinvoker.api.element.SingerApiElement;

import java.util.List;

public interface SingerService {
    List<SingerApiElement> findAll();

    List<SingerApiElement> findByFirstName(String firstName);

    SingerApiElement findById(Long id);

    SingerApiElement save(SingerApiElement singer);

    void delete(SingerApiElement singer);
}
