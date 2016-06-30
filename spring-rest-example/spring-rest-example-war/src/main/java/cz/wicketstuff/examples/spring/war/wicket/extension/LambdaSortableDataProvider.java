/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.wicketstuff.examples.spring.war.wicket.extension;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class LambdaSortableDataProvider<T extends Serializable, S> extends SortableDataProvider<T, S> {

	private static final long serialVersionUID = 1L;

	private final BiFunction<Long, Long, Iterator<? extends T>> iterator;
	
	private final Supplier<Long> size;
	
	public LambdaSortableDataProvider(
			BiFunction<Long, Long, Iterator<? extends T>> iterator, Supplier<Long> size) {
		super();
		this.iterator = iterator;
		this.size = size;
	}

	@Override
	public Iterator<? extends T> iterator(long first, long count) {
		return iterator.apply(first, count);
	}

	@Override
	public IModel<T> model(T object) {
		return new Model<>(object);
	}

	@Override
	public long size() {
		return size.get();
	}

}
