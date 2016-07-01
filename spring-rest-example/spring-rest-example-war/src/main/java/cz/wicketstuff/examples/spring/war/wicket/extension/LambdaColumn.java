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
import java.util.function.Consumer;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class LambdaColumn<T, S> extends AbstractColumn<T, S> {

	private static final long serialVersionUID = 1L;
	
	private final Consumer<Populating<T>> populator;

	public LambdaColumn(IModel<String> displayModel, S sortProperty, Consumer<Populating<T>> populator) {
		super(displayModel, sortProperty);
		this.populator = populator;
	}

	public LambdaColumn(IModel<String> displayModel, Consumer<Populating<T>> populator) {
		super(displayModel);
		this.populator = populator;
	}

	@Override
	public final void populateItem(Item<ICellPopulator<T>> cellItem,
			String componentId, IModel<T> rowModel) {
		populator.accept(new Populating<T>(cellItem, componentId, rowModel));
	}
	
	public static class Populating<T> implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		public final Item<ICellPopulator<T>> cellItem;
		public final String componentId;
		public final IModel<T> rowModel;
		
		public Populating(Item<ICellPopulator<T>> cellItem, String componentId,
				IModel<T> rowModel) {
			super();
			this.cellItem = cellItem;
			this.componentId = componentId;
			this.rowModel = rowModel;
		}

	} 

	
}
