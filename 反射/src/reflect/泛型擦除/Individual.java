package reflect.泛型擦除;

public class Individual implements Comparable<Individual> {
    private static long counter = 0;
    private final long id = counter++;
    private String name; // name is optional

    public long id() {
        return id;
    }

    public Individual(String name) {
        this.name = name;
    }

    public Individual() {
    }

    public String toString() {
        return getClass().getSimpleName() + (name == null ? "" : " " + name);
    }

    public boolean equals(Object o) {
        return o instanceof Individual && id == ((Individual) o).id;
    }

    public int hashCode() {
        int result = 17;
        if (name != null) {
            result = 37 * result + name.hashCode();
        }
        result = 37 * result + (int) id;
        return result;
    }

    //Comparable接口中的方法
    public int compareTo(Individual arg) {
        // Compare by class name first:
        String first = getClass().getSimpleName();
        String argFirst = arg.getClass().getSimpleName();
        int firstCompare = first.compareTo(argFirst);
        if (firstCompare != 0) {
            return firstCompare;
        }
        if (name != null && arg.name != null) {
            int secendCompare = name.compareTo(arg.name);
            if (secendCompare != 0) {
                return secendCompare;
            }
        }
        return (arg.id < id ? -1 : (arg.id == id ? 0 : 1));
    }
}