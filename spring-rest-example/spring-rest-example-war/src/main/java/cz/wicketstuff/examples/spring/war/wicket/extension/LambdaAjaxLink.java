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

import java.util.function.BiConsumer;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class LambdaAjaxLink<T> extends AjaxLink<T> {

	private static final long serialVersionUID = 1L;
	
	private final BiConsumer<AjaxRequestTarget, IModel<T>> onClick;

	public LambdaAjaxLink(String id, IModel<T> model, BiConsumer<AjaxRequestTarget, IModel<T>> onClick) {
		super(id, model);
		this.onClick = onClick;
	}

	public LambdaAjaxLink(String id, BiConsumer<AjaxRequestTarget, IModel<T>> onClick) {
		this(id, null, onClick);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void onClick(AjaxRequestTarget target) {
		onClick.accept(target, (IModel<T>) getDefaultModel());
	}
	
	

}
