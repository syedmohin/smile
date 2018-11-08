/*******************************************************************************
 * Copyright (c) 2010 Haifeng Li
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package smile.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Nominal data type. Nominal variables take on a limited
 * number of unordered values; such variables are often referred to
 * as categorical variables.
 *
 * Both integer and character variables can be made into nominal,
 * but a nominal's levels will always be character values.
 *
 * @author Haifeng Li
 */
public class NominalType implements DataType {
    /**
     * The string values of the nominal attribute.
     */
    private String[] levels;
    /**
     * Map a string to an integer level.
     */
    private HashMap<String, Integer> map = new HashMap<>();

    /**
     * Constructor.
     * @param values the levels of nominal values.
     */
    public NominalType(String... values) {
        this.levels = values;
        for (int i = 0; i < values.length; i++) {
            map.put(values[i], i);
        }
    }

    /**
     * Constructor.
     * @param values the levels of nominal values.
     */
    public NominalType(int... values) {
        this.levels = new String[values.length];
        this.levels = Arrays.stream(values).mapToObj(v -> String.valueOf(v)).collect(Collectors.toList()).toArray(this.levels);

        for (int i = 0; i < values.length; i++) {
            map.put(this.levels[i], i);
        }
    }

    @Override
    public String name() {
        return String.format("nominal%s", Arrays.toString(levels));
    }

    @Override
    public Integer valueOf(String s) {
        return map.get(s);
    }

    public int size() {
        return levels.length;
    }

    public String[] levels() {
        return levels;
    }
}
