/*
 * Copyright (c) 2011 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.common.truth;

import com.google.common.annotations.GwtIncompatible;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Reflection utility methods.
 *
 * @author Christian Gruber (cgruber@israfil.net)
 */
@GwtIncompatible("java.lang.reflect.*")
final class ReflectionUtil {
  private ReflectionUtil() {}

  /** Returns the captured type. */
  public static Class<?> typeParameter(Class<?> clazz, int paramIndex) {
    Type superclass = clazz.getGenericSuperclass();
    if (!(superclass instanceof ParameterizedType)) {
      throw new IllegalArgumentException("" + superclass + " isn't parameterized");
    }
    Type[] typeParams = ((ParameterizedType) superclass).getActualTypeArguments();
    if (typeParams[paramIndex] instanceof ParameterizedType) {
      typeParams[paramIndex] = ((ParameterizedType) typeParams[paramIndex]).getRawType();
    }
    return (Class<?>) typeParams[paramIndex];
  }
}
