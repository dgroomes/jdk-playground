module dgroomes {
    requires org.slf4j;
    requires io.github.classgraph;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j.simple;

    // Jackson uses reflection for its object mapping work. For example, when Jackson is deserializing a JSON string to
    // an instance of dgroomes.MyMessage, Jackson needs to be allowed to access dgroomes.MyMessage. JPMS forbids Jackson
    // access to this class unless we explicitly "open" the class's owning package ("dgroomes") to the Jackson module.
    // The below line does exactly that.
    //
    // Editorial note: On one hand, these "opens" declarations are an inconvenience and a maintenance burden but on the
    // other hand it makes us more aware of dependency relationships and ostensibly prevents us from unknowingly baking
    // in new dependency relationships that we didn't intend.
    opens dgroomes to com.fasterxml.jackson.databind;
}
