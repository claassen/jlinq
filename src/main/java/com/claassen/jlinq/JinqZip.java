//package jinq;
//
//import java.util.List;
//
//public class JinqZip<T> extends JLinqBase<T> {
//
//    private int which;
//
//    public JinqZip(int startWhich, List<JLinqBase<T>> sources) {
//        which = startWhich;
//
//        setNext(() -> {
//            T item = null;
//
//            while(!sources.get(which++)._hasNext.get()) {
//                if(which > sources.size() - 1) {
//                    //TODO: throw exception?
//                    return null;
//                }
//            }
//
//            if(which > sources.length - 1) {
//                which = 0;
//            }
//        });
//    }
//}
