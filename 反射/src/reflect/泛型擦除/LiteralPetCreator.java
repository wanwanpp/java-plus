package reflect.泛型擦除;

import java.util.*;

public class LiteralPetCreator extends PetCreator {

    @SuppressWarnings("unchecked")
    public static final List<Class<? extends Pet>> allTypes = Collections.unmodifiableList(
            Arrays.asList(Pet.class, Dog.class, Cat.class, Mutt.class, EgyptianMau.class));
    private static final List<Class<? extends Pet>> types = allTypes.subList(
            allTypes.indexOf(Mutt.class), allTypes.size());

    public List<Class<? extends Pet>> getTypes() {
        return types;
    }

    public static void main(String[] args) {
        PetCreator petCreator = new LiteralPetCreator();
        ArrayList<Pet> pets = petCreator.arrayList(5);//随机生成一个size为5的ArrayList<Pet>对象

        //计算pets中有多少个对象属于那种子类类型。
        TypeCounter typeCounter = new TypeCounter(Pet.class);
        Iterator<Pet> iterator = pets.iterator();
        while (iterator.hasNext()){
            typeCounter.count(iterator.next());
        }
        System.out.println(typeCounter.toString());
    }
}