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
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Martin Strejc (strma17)
 *
 */
public class LambdaAjaxButton extends AjaxButton {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(LambdaAjaxButton.class);
	
	
	private final BiConsumer<AjaxRequestTarget, Form<?>> onSubmit;
	private final BiConsumer<AjaxRequestTarget, Form<?>> onError;

	public LambdaAjaxButton(String id, Form<?> form, BiConsumer<AjaxRequestTarget, Form<?>> onSubmit, BiConsumer<AjaxRequestTarget, Form<?>> onError) {
		super(id, form);
		this.onSubmit = onSubmit;
		this.onError = onError;
		checkExecutors(id, onSubmit, onError);
	}

	public LambdaAjaxButton(String id, IModel<String> model, Form<?> form, BiConsumer<AjaxRequestTarget, Form<?>> onSubmit, BiConsumer<AjaxRequestTarget, Form<?>> onError) {
		super(id, model, form);
		this.onSubmit = onSubmit;
		this.onError = onError;
		checkExecutors(id, onSubmit, onError);
	}

	public LambdaAjaxButton(String id, IModel<String> model, BiConsumer<AjaxRequestTarget, Form<?>> onSubmit, BiConsumer<AjaxRequestTarget, Form<?>> onError) {
		super(id, model);
		this.onSubmit = onSubmit;
		this.onError = onError;
		checkExecutors(id, onSubmit, onError);
	}

	public LambdaAjaxButton(String id, BiConsumer<AjaxRequestTarget, Form<?>> onSubmit, BiConsumer<AjaxRequestTarget, Form<?>> onError) {
		super(id);
		this.onSubmit = onSubmit;
		this.onError = onError;
		checkExecutors(id, onSubmit, onError);
	}
	
	private void checkExecutors(String id, BiConsumer<AjaxRequestTarget, Form<?>> onSubmit, BiConsumer<AjaxRequestTarget, Form<?>> onError) {
		if (onSubmit == null) {
			log.warn("onSubmit is null for component {}", id);
		}
		if (onError == null) {
			log.warn("onError is null for component {}", id);
		}
	}
	
	@Override
	protected final void onSubmit(AjaxRequestTarget target, Form<?> form) {
		if (onSubmit != null) {
			onSubmit.accept(target, form);
		}
	}
	
	@Override
	protected final void onError(AjaxRequestTarget target, Form<?> form) {
		if (onError != null) {
			onError.accept(target, form);
		}
	}

}
