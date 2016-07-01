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
	
	private final AjaxSubmit submit;
	private final AjaxSubmit error;

	public LambdaAjaxButton(String id, Form<?> form, AjaxSubmit submit, AjaxSubmit error) {
		super(id, form);
		this.submit = submit;
		this.error = error;
		checkExecutors(id, submit, error);
	}

	public LambdaAjaxButton(String id, IModel<String> model, Form<?> form, AjaxSubmit submit, AjaxSubmit error) {
		super(id, model, form);
		this.submit = submit;
		this.error = submit;
		checkExecutors(id, submit, error);
	}

	public LambdaAjaxButton(String id, IModel<String> model, AjaxSubmit submit, AjaxSubmit error) {
		super(id, model);
		this.submit = submit;
		this.error = error;
		checkExecutors(id, submit, error);
	}

	public LambdaAjaxButton(String id, AjaxSubmit submit, AjaxSubmit error) {
		super(id);
		this.submit = submit;
		this.error = error;
		checkExecutors(id, submit, error);
	}
	
	private void checkExecutors(String id, AjaxSubmit submit, AjaxSubmit error) {
		if (submit == null) {
			log.warn("submit is null for component {}", id);
		}
		if (error == null) {
			log.warn("error is null for component {}", id);
		}
	}
	
	@Override
	protected final void onSubmit(AjaxRequestTarget target, Form<?> form) {
		if (submit != null) {
			submit.ajaxFormAction(target, form);
		}
	}
	
	@Override
	protected final void onError(AjaxRequestTarget target, Form<?> form) {
		if (error != null) {
			error.ajaxFormAction(target, form);
		}
	}

	@FunctionalInterface
	public static interface AjaxSubmit extends Serializable {
		
		void ajaxFormAction(AjaxRequestTarget target, Form<?> form);
		
	}

}
