package pl.us.tripsbooking.security.utils;

import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityDtoMapper {
    public <T> T mapClass(Tuple tuple, Class<T> destinationClass) {
        try {
            List<TupleElement<?>> elements = tuple.getElements();
            T t = destinationClass.newInstance();
            Method[] methods = destinationClass.getMethods();
            for (TupleElement<?> element : elements) {
                Class javaType = element.getJavaType();
                String alias = element.getAlias();

                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase("set" + alias)) {
                        if (javaType.equals(Object.class))
                            method.invoke(t, new Object[]{null});
                        else
                            method.invoke(t, (javaType.cast(tuple.get(alias))));

                        break;
                    }
                }
            }
            return t;
        } catch (Exception exc) {
            return null;
        }
    }

    public <T> List<T> mapList(List<Tuple> tuples, Class<T> destinationClass) {
        List<T> list = new ArrayList<>();
        for (Tuple tuple : tuples) {
            T mappedObject = mapClass(tuple, destinationClass);
            if (mappedObject != null)
                list.add(mappedObject);
            else
                return null;
        }
        return list;
    }
}
