package com.template.spring.core.validator;

@FunctionalInterface
public interface Validation<Entity> {

    GenericValidationResult test(Entity param);

    default Validation<Entity> and(Validation<Entity> other) {
        return param -> {
            GenericValidationResult result = this.test(param);
            return !result.isValid() ? result : other.test(param);
        };
    }

    default Validation<Entity> or(Validation<Entity> other) {
        return param -> {
            GenericValidationResult result = this.test(param);
            return result.isValid() ? result : other.test(param);
        };
    }
}
