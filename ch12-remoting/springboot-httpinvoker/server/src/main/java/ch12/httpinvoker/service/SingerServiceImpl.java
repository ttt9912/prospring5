package ch12.httpinvoker.service;

import ch12.httpinvoker.api.element.SingerApiElement;
import ch12.httpinvoker.api.service.SingerService;
import ch12.httpinvoker.data.Singer;
import ch12.httpinvoker.data.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DependsOn("conversionService")
@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional(readOnly = true)
    public List<SingerApiElement> findAll() {
        return Stream.of(singerRepository.findAll())
                .map(entity -> conversionService.convert(entity, SingerApiElement.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SingerApiElement> findByFirstName(String firstName) {
        return Stream.of(singerRepository.findByFirstName(firstName))
                .map(entity -> conversionService.convert(entity, SingerApiElement.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SingerApiElement findById(Long id) {
        final Singer entity = singerRepository.findById(id).get();
        return conversionService.convert(entity, SingerApiElement.class);
    }

    @Override
    public SingerApiElement save(SingerApiElement singer) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(SingerApiElement singer) {
        throw new NotImplementedException();
    }
}
