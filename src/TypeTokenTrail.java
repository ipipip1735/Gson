import com.google.gson.reflect.TypeToken;
import entity.Mk;

import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by Administrator on 2020/11/6 14:18.
 */
public class TypeTokenTrail {

    public static void main(String[] args) {

        TypeTokenTrail typeTokenTrail = new TypeTokenTrail();
//        typeTokenTrail.forList();
//        typeTokenTrail.forMap();
//        typeTokenTrail.forInner();
        typeTokenTrail.prase();
    }

    private void prase() {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("one");

        System.out.println(arrayList.getClass());

        TypeToken typeToken = new TypeToken<ArrayList<String>>() {
        };
        Object t = arrayList;
        if (t.getClass().equals(typeToken.getRawType())) {
            //Collection
            if (Collection.class.isAssignableFrom(t.getClass())) {
                Collection collection = (Collection) t;
                if (!collection.isEmpty()) {
                    ParameterizedType parameterized = (ParameterizedType) typeToken.getType();
                    if (collection.toArray()[0].getClass().equals(parameterized.getActualTypeArguments()[0])) {
                        System.out.println("ok");

                    }
                }

            }



        }
    }

    private void forInner() {
        TypeToken typeToken = new TypeToken<Mk.DJ<Integer>>() {
        };
        ParameterizedType parameterized = (ParameterizedType) typeToken.getType();
        System.out.println(parameterized.getOwnerType());
        System.out.println(parameterized.getRawType());
        System.out.println(parameterized.getActualTypeArguments()[0]);
    }

    private void forMap() {
        TypeToken typeToken = new TypeToken<Map<String, List<Integer>>>() {
        };
        System.out.println(typeToken.getType());
        System.out.println(typeToken.getRawType());

        ParameterizedType parameterized = (ParameterizedType) typeToken.getType();
        for (int i = 0; i < parameterized.getActualTypeArguments().length; i++) {
            System.out.println(parameterized.getActualTypeArguments()[i]);
        }

        System.out.println(parameterized.getRawType());
        System.out.println(parameterized.getOwnerType());

    }

    private void forList() {
        TypeToken typeToken = new TypeToken<List<Integer>>() {
        };
        ParameterizedType parameterized = (ParameterizedType) typeToken.getType();
        System.out.println(parameterized.getActualTypeArguments()[0]);


        try {
            Class c = Class.forName(parameterized.getActualTypeArguments()[0].getTypeName());
            System.out.println(c.equals(String.class));
            System.out.println(c.equals(Integer.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //方法二：通过TypeToken获取Class
        System.out.println(TypeToken.get(parameterized.getActualTypeArguments()[0]).getRawType().equals(Integer.class));
    }
}
