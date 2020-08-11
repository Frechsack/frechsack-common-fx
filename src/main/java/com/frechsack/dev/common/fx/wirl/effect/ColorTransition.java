package com.frechsack.dev.common.fx.wirl.effect;

import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Performs a {@link Transition} between two colors.
 */
public class ColorTransition extends Transition
{
    private ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<>((e)->{})
    {
        @Override
        public Object getBean()
        {
            return ColorTransition.this;
        }

        @Override
        public String getName()
        {
            return "onAction";
        }
    };

    public EventHandler<ActionEvent> getOnAction()
    {
        return onAction.get();
    }

    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty()
    {
        return onAction;
    }

    public void setOnAction(EventHandler<ActionEvent> onAction)
    {
        this.onAction.set(onAction);
    }

    private ObjectProperty<Duration> duration = new ObjectPropertyBase<>(Duration.seconds(1))
    {
        @Override
        public void invalidated()
        {
            try
            {
                setCycleDuration(duration.get());
            }
            catch (IllegalArgumentException e)
            {
                if (isBound())
                {
                    unbind();
                }
                set(getCycleDuration());
                throw e;
            }
        }

        @Override
        public Object getBean()
        {
            return ColorTransition.this;
        }

        @Override
        public String getName()
        {
            return "duration";
        }
    };

    public final ObjectProperty<Duration> durationProperty()
    {
        return duration;
    }
    public Duration getDuration()
    {
        return duration.get();
    }
    public void setDuration(Duration duration)
    {
        this.duration.set(duration);
    }

    private ObjectProperty<Color> baseFrom = new ObjectPropertyBase<>()
    {
        @Override
        public Object getBean()
        {
            return ColorTransition.this;
        }

        @Override
        public String getName()
        {
            return "baseFrom";
        }
    };
    public final ObjectProperty<Color> baseFromProperty()
    {
        return baseFrom;
    }
    public void setBaseFrom(Color baseFrom)
    {
        this.baseFrom.set(baseFrom);
    }
    public Color getBaseFrom()
    {
        return baseFrom.get();
    }

    private ObjectProperty<Color> baseTo = new ObjectPropertyBase<>()
    {
        @Override
        public Object getBean()
        {
            return ColorTransition.this;
        }

        @Override
        public String getName()
        {
            return "baseTo";
        }
    };
    public final ObjectProperty<Color> baseToProperty()
    {
        return baseTo;
    }
    public void setBaseTo(Color baseTo)
    {
        this.baseTo.set(baseTo);
    }
    public Color getBaseTo()
    {
        return baseTo.get();
    }



    private Color base;

    public Color getBase()
    {
        return base;
    }


    /**
     * The constructor of {@code WirlNodeTransition}
     */
    public ColorTransition()
    {
        super();
    }

    private static final ActionEvent cachedActionEvent = new ActionEvent();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void interpolate(double frac)
    {
        base   = baseFrom.get().interpolate(baseTo.get(), frac);
        onAction.get().handle(cachedActionEvent);
    }
}
