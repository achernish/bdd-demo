package com.inatec.api.test.service;

import com.inatec.api.test.model.*;

/**
 * @author Anatoly Chernysh
 */
public interface InatecCreditCardAPI {

    public Response authorize(AuthorizeRequest request);

    public Response preAuthorize(AuthorizeRequest request);

    public Response capture(CaptureRequest request);

    public Response partialCapture(PartialCaptureRequest request);

    public Response refund(RefundRequest request);

    public Response reversal(ReversalRequest request);
}
